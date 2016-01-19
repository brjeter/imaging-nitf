package org.codice.imaging.nitf.core.label;

import org.codice.imaging.nitf.core.RGBColour;
import org.codice.imaging.nitf.core.common.CommonNitfSubSegment;


/**
 Label segment subheader information (NITF 2.0 only).
 */
public interface LabelSegmentHeader extends CommonNitfSubSegment {

    /**
     Return the row part of the label location (LLOC).
     <p>
     "A label's location specified by providing the location of the upper left corner of the
     minimum bounding rectangle of the label. This field shall contain the label location
     represented as rrrrrccccc, where rrrrr and ccccc are the row and the column offset
     from the ILOC, SLOC, or LLOC value of the item to which the label is attached.
     A row or column value of 00000 indicates no offset. Positive row and column
     values indicate offsets down and to the right and range from 00001 to 99999, while
     negative row and column values indicate offsets up and to the left and must be
     within the range -0001 to -9999. The coordinate system used to express ILOC,
     SLOC, and LLOC fields shall be common for all images, labels, and symbols in the
     file having attachment level zero. The location in this common coordinate system
     of all displayable graphic components can be computed from the offsets given in the
     ILOC, SLOC, and LLOC fields."

     @return the row number for the label location.
     */
    int getLabelLocationRow();


    /**
     Return the column part of the label location (LLOC).
     <p>
     "A label's location specified by providing the location of the upper left corner of the
     minimum bounding rectangle of the label. This field shall contain the label location
     represented as rrrrrccccc, where rrrrr and ccccc are the row and the column offset
     from the ILOC, SLOC, or LLOC value of the item to which the label is attached.
     A row or column value of 00000 indicates no offset. Positive row and column
     values indicate offsets down and to the right and range from 00001 to 99999, while
     negative row and column values indicate offsets up and to the left and must be
     within the range -0001 to -9999. The coordinate system used to express ILOC,
     SLOC, and LLOC fields shall be common for all images, labels, and symbols in the
     file having attachment level zero. The location in this common coordinate system
     of all displayable graphic components can be computed from the offsets given in the
     ILOC, SLOC, and LLOC fields."

     @return the column number for the label location.
     */
    int getLabelLocationColumn();

    /**
     Returns the label cell width (LCW) of the label.
     <p>
     "This field shall contain the width in pixels of the character cell (rectangular array
     used to contain a single character in monospaced fonts) used by the file originator.
     The default value of 00 indicates the file originator has not included this information."

     @return cell width (default 0, not set)
     */
    int getLabelCellWidth();

    /**
     Returns the label cell height (LCH) of the label.
     <p>
     "This field shall contain the height in pixels of the character cell (rectangular array
     used to contain a single character in monospaced fonts) used by the file originator.
     The default value of 00 indicates the file originator has not included this information."

     @return cell height (default 0, not set)
     **/
    int getLabelCellHeight();

    /**
     Return the label display level (LDLVL).
     <p>
     "This field shall contain a valid value that indicates the graphic display level of the
     label relative to other displayed file components in a composite display. The valid
     values are 001 to 999. The display level of each displayable file component (image,
     label, symbol) within a file shall be unique; that is, each number from 001 to 999 is
     the display level of, at most, one item. The meaning of display level is discussed
     fully in 5.3.3. The symbol, image, or label component in the file having the
     minimum display level shall have attachment level zero (ILOC, SLOC, and LLOC
     field descriptions)."

     @return the label display level
     */
    int getLabelDisplayLevel();

    /**
     Return the label text colour.

     @return the text colour.
     */
    RGBColour getLabelTextColour();

    /**
     Return the label background colour.

     @return the background colour.
     */
    RGBColour getLabelBackgroundColour();

    /**
     Return the label data length.

     @return the label data segment length, in bytes
     */
    int getLabelDataLength();

    /**
     Set the label segment data length.
     <p>
     This is the length of the contents of the associated data segment.

     @param length the label data segment length, in bytes
     */
    void setLabelSegmentDataLength(int length);
}
