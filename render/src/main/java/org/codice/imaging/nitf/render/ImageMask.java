/*
 * Copyright (c) Codice Foundation
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 *
 */

package org.codice.imaging.nitf.render;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.ImageInputStream;
import org.codice.imaging.nitf.core.ImageMode;

import org.codice.imaging.nitf.core.NitfImageSegmentHeader;

public final class ImageMask {

    private ImageInputStream mImageInputStream = null;
    private NitfImageSegmentHeader mImageSegmentHeader = null;

    private int[][] bmrnbndm = null;
    private final List<Integer> tmrnbndm = new ArrayList<>();
    int tpxcd = -1;

    private static final int BLOCK_NOT_RECORDED = 0xFFFFFFFF;

    public ImageMask(final NitfImageSegmentHeader segmentHeader, final ImageInputStream imageStream) throws IOException {
        mImageSegmentHeader = segmentHeader;
        mImageInputStream = imageStream;
        readImageMask();
    }

    private void readImageMask() throws IOException {
        int imdatoff = mImageInputStream.readInt();
        int bmrlnth = mImageInputStream.readShort();
        int tmrlnth = mImageInputStream.readShort();
        int tpxcdlnth = mImageInputStream.readShort();
        System.out.println(String.format("Blocked image data offset: 0x%08x", imdatoff));
        System.out.println(String.format("Block mask record length: 0x%04x", bmrlnth));
        System.out.println(String.format("Pad Pixel Mask Record Length: 0x%04x", tmrlnth));
        System.out.println(String.format("Pad Output pixel code length: 0x%04x", tpxcdlnth));
        if (tpxcdlnth > 0) {
            tpxcd = 0;
            int numBytesToRead = (tpxcdlnth + 7) / 8;
            System.out.println("Reading TPXCD at length:" + numBytesToRead);
            int bandBits = (int)mImageInputStream.readBits(numBytesToRead * 8);
            for (int i = 0; i < mImageSegmentHeader.getNumBands(); ++i) {
                tpxcd = tpxcd | (bandBits << (8*i));
            }
            System.out.println(String.format("Pad Output pixel code : 0x%08x", tpxcd));
        }
        int numBandsToRead = 1;
        if (mImageSegmentHeader.getImageMode() == ImageMode.BANDSEQUENTIAL) {
            numBandsToRead = mImageSegmentHeader.getNumBands();
        }
        if (bmrlnth > 0) {
            bmrnbndm = new int[mImageSegmentHeader.getNumberOfBlocksPerRow() * mImageSegmentHeader.getNumberOfBlocksPerColumn()][mImageSegmentHeader.getNumBands()];
            for (int m = 0; m < numBandsToRead; ++m) {
                for (int n = 0; n < mImageSegmentHeader.getNumberOfBlocksPerRow() * mImageSegmentHeader.getNumberOfBlocksPerColumn(); ++n) {
                    bmrnbndm[n][m] = mImageInputStream.readInt();
                    System.out.println(String.format("mask blocks (band %d) %d: 0x%08x", m, n, bmrnbndm[n][m]));
                }
            }
        }
        if (tmrlnth > 0) {
            for (int m = 0; m < numBandsToRead; ++m) {
                for (int n = 0; n < mImageSegmentHeader.getNumberOfBlocksPerRow() * mImageSegmentHeader.getNumberOfBlocksPerColumn(); ++n) {
                    int val = mImageInputStream.readInt();
                    tmrnbndm.add(val);
                    System.out.println(String.format("mask pixel (band %d) %d: 0x%08x", m, n, val));
                }
            }
        }
        System.out.println(String.format("Offset at the end of the header: 0x%08x", mImageInputStream.getStreamPosition()));
    }

    public boolean isMaskedBlock(int blockNumber, int bandNumber) {
        if (bmrnbndm == null) {
            return false;
        }
        return (BLOCK_NOT_RECORDED == bmrnbndm[blockNumber][bandNumber]);
    }
    
    public boolean isPadPixel(int value) {
        if (tpxcd == -1) {
            return false;
        }
        return (tpxcd == value);
    }

}
