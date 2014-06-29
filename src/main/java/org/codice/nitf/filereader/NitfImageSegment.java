/**
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
 **/
package org.codice.nitf.filereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NitfImageSegment
{
    NitfReader reader = null;

    private String imageIdentifier1 = null;
    private Date imageDateTime = null;
    // TODO: consider making this a class (BE/O-suffix + country code) if we can find examples
    private String imageTargetId = null;
    private String imageIdentifier2 = null;
    private NitfSecurityMetadata securityMetadata = null;
    private String imageSource = null;

    private static final String IM = "IM";
    private static final int IM_LENGTH = 2;
    private static final int IID1_LENGTH = 10;
    private static final int TGTID_LENGTH = 17;
    private static final int IID2_LENGTH = 80;
    private static final int ISORCE_LENGTH = 42;

    public NitfImageSegment(BufferedReader nitfBufferedReader, int offset) throws ParseException {
        reader = new NitfReader(nitfBufferedReader, offset);
        readIM();
        readIID1();
        readIDATIM();
        readTGTID();
        readIID2();
        securityMetadata = new NitfSecurityMetadata(reader);
        reader.readENCRYP();
        readISORCE();
    }

    public String getImageIdentifier1() {
        return imageIdentifier1;
    }

    public Date getImageDateTime() {
        return imageDateTime;
    }

    public String getImageTargetId() {
        return imageTargetId;
    }

    public String getImageIdentifier2() {
        return imageIdentifier2;
    }

    public NitfSecurityMetadata getSecurityMetadata() {
        return securityMetadata;
    }

    public String getImageSource() {
        return imageSource;
    }

    private void readIM() throws ParseException {
       reader.verifyHeaderMagic(IM);
    }

    private void readIID1() throws ParseException {
        imageIdentifier1 = reader.readTrimmedBytes(IID1_LENGTH);
    }

    private void readIDATIM() throws ParseException {
        imageDateTime = reader.readNitfDateTime();
    }

    private void readTGTID() throws ParseException {
        imageTargetId = reader.readTrimmedBytes(TGTID_LENGTH);
    }

    private void readIID2() throws ParseException {
        imageIdentifier2 = reader.readTrimmedBytes(IID2_LENGTH);
    }

    private void readISORCE() throws ParseException {
        imageSource = reader.readTrimmedBytes(ISORCE_LENGTH);
    }
}