/*
 * Copyright (C) 2014 Codice Foundation
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package org.codice.imaging.nitf.render;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

import junit.framework.TestCase;
import org.codice.imaging.nitf.core.ImageDataExtractionParseStrategy;
import org.codice.imaging.nitf.core.NitfFileFactory;
import org.codice.imaging.nitf.core.NitfImageSegmentHeader;
import org.junit.Test;

/**
 * Tests for JITC NITF 2.0 and NITF 2.1 test cases.
 * See:
 * http://www.gwg.nga.mil/ntb/baseline/software/testfile/Nitfv2_0/scen_2_0.html and
 * http://www.gwg.nga.mil/ntb/baseline/software/testfile/Nitfv2_1/scen_2_1.html
 */
public class RenderJitcTest extends TestCase {

    public RenderJitcTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testNS3038A() throws IOException, ParseException  {
        testOneFile("ns3038a.nsf", "bilevel");
    }

    @Test
    public void testI_3041A() throws IOException, ParseException  {
        testOneFile("i_3041a.ntf", "bilevel");
    }

    @Test
    public void testNS3050A() throws IOException, ParseException  {
        testOneFile("ns3050a.nsf", "bilevel");
    }

    @Test
    public void testU_1036A() throws IOException, ParseException  {
        testOneFile("U_1036A.NTF", "bilevel");
    }

    @Test
    public void testU_1050A() throws IOException, ParseException  {
        testOneFile("U_1050A.NTF", "bilevel");
    }

    @Test
    public void testU_4003B() throws IOException, ParseException  {
        testOneFile("U_4003B.NTF", "bilevel");
    }

    @Test
    public void testU_4004B() throws IOException, ParseException  {
        testOneFile("U_4004B.NTF", "bilevel");
    }

    @Test
    public void testU_1001A() throws IOException, ParseException  {
        testOneFile("U_1001A.NTF", "uncompressed");
    }

    @Test
    public void testU_1034A() throws IOException, ParseException  {
        testOneFile("U_1034A.NTF", "uncompressed");
    }

    @Test
    public void testU_1101A() throws IOException, ParseException  {
        testOneFile("U_1101A.NTF", "uncompressed");
    }

    @Test
    public void testU_1122A() throws IOException, ParseException  {
        testOneFile("U_1122A.NTF", "uncompressed");
    }

    @Test
    public void testU_2001A() throws IOException, ParseException  {
        testOneFile("U_2001A.NTF", "uncompressed");
    }

    @Test
    public void testU_3002A() throws IOException, ParseException  {
        testOneFile("U_3002A.NTF", "uncompressed");
    }

    @Test
    public void testU_3010A() throws IOException, ParseException  {
        testOneFile("U_3010A.NTF", "uncompressed");
    }

    @Test
    public void testU_3050B() throws IOException, ParseException  {
        testOneFile("U_3050B.NTF", "uncompressed");
    }

    @Test
    public void testU_4002A() throws IOException, ParseException  {
        testOneFile("U_4002A.NTF", "uncompressed");
    }

    @Test
    public void testU_4005A() throws IOException, ParseException  {
        testOneFile("U_4005A.NTF", "uncompressed");
    }

    @Test
    public void testU_4007A() throws IOException, ParseException  {
        testOneFile("U_4007A.NTF", "uncompressed");
    }

    @Test
    public void testI_3001A() throws IOException, ParseException  {
        testOneFile("i_3001a.ntf", "uncompressed");
    }

    @Test
    public void testI_3004G() throws IOException, ParseException  {
        testOneFile("i_3004g.ntf", "uncompressed");
    }

    @Test
    public void testI_3201C() throws IOException, ParseException  {
        testOneFile("i_3201c.ntf", "uncompressed");
    }

    @Test
    public void testI_3034C() throws IOException, ParseException  {
        testOneFile("i_3034c.ntf", "uncompressed");
    }

    @Test
    public void testI_3034F() throws IOException, ParseException  {
        testOneFile("i_3034f.ntf", "uncompressed");
    }

    @Test
    public void testI_3228C() throws IOException, ParseException  {
        testOneFile("i_3228c.ntf", "uncompressed");
    }

    @Test
    public void testNS3228D() throws IOException, ParseException  {
        testOneFile("ns3228d.nsf", "uncompressed");
    }

    @Test
    public void testI_3228E() throws IOException, ParseException  {
        testOneFile("i_3228e.ntf", "uncompressed");
    }

    @Test
    public void testNS3301B() throws IOException, ParseException  {
        testOneFile("ns3301b.nsf", "uncompressed");
    }

    @Test
    public void testNS3301E() throws IOException, ParseException  {
        testOneFile("ns3301e.nsf", "uncompressed");
    }

    @Test
    public void testI_3301H() throws IOException, ParseException  {
        testOneFile("i_3301h.ntf", "uncompressed");
    }

    @Test
    public void testI_3301K() throws IOException, ParseException  {
        testOneFile("i_3301k.ntf", "uncompressed");
    }

    @Test
    public void testNS3302A() throws IOException, ParseException  {
        testOneFile("ns3302a.nsf", "uncompressed");
    }

    @Test
    public void testI_3303A() throws IOException, ParseException  {
        testOneFile("i_3303a.ntf", "uncompressed");
    }

    @Test
    public void testNS3310A() throws IOException, ParseException  {
        testOneFile("ns3310a.nsf", "uncompressed");
    }

    @Test
    public void testI_3405A() throws IOException, ParseException  {
        testOneFile("i_3405a.ntf", "uncompressed");
    }

    @Test
    public void testI_3430A() throws IOException, ParseException  {
        testOneFile("i_3430a.ntf", "uncompressed");
    }

    @Test
    public void testNS3004F() throws IOException, ParseException  {
        testOneFile("ns3004f.nsf", "uncompressed");
    }

    @Test
    public void testNS3034D() throws IOException, ParseException  {
        testOneFile("ns3034d.nsf", "uncompressed");
    }

    @Test
    public void testNS3201A() throws IOException, ParseException  {
        testOneFile("ns3201a.nsf", "uncompressed");
    }

    @Test
    public void testV_3301F() throws IOException, ParseException  {
        testOneFile("v_3301f.ntf", "uncompressed");
    }

    @Test
    public void testU_3058B() throws IOException, ParseException {
        testOneFile("U_3058B.NTF", "vq");
    }

    private void testOneFile(final String testfile, final String parentDirectory) throws IOException, ParseException {
        String inputFileName = "/" + parentDirectory + "/" + testfile;
        System.out.println("================================== Testing :" + inputFileName);
        assertNotNull("Test file missing: " + inputFileName, getClass().getResource(inputFileName));
        ImageDataExtractionParseStrategy parseStrategy = new ImageDataExtractionParseStrategy();
        NitfFileFactory.parse(getClass().getResourceAsStream(inputFileName), parseStrategy);
        NitfImageSegmentHeader imageSegment = parseStrategy.getImageSegmentHeaders().get(0);
        NitfRender renderer = new NitfRender();
        BufferedImage img = new BufferedImage(imageSegment.getImageLocationColumn() + (int)imageSegment.getNumberOfColumns(),
                                    imageSegment.getImageLocationRow() + (int)imageSegment.getNumberOfRows(),
                                    BufferedImage.TYPE_INT_ARGB);
        ByteArrayInputStream nitfImageDataStream = new ByteArrayInputStream(parseStrategy.getImageSegmentData().get(0));
        ImageInputStream imageInputStream = new MemoryCacheImageInputStream(nitfImageDataStream);
        renderer.render(imageSegment, imageInputStream, img.createGraphics());
        // TODO: move to automated (perceptual?) comparison
        ImageIO.write(img, "png", new File(testfile + ".png"));
    }
}
