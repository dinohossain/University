package com.dating.app.idateu.Homepage.DBConnector;

import java.sql.*;
import java.io.*;
import java.lang.reflect.*;


/**
 * A serialized mapping in the Java programming language of an SQL
 * <code>BLOB</code> value.
 * <P>
 * The <code>SerialBlob</code> class provides a constructor for creating
 * an instance from a <code>Blob</code> object.  Note that the
 * <code>Blob</code>
 * object should have brought the SQL <code>BLOB</code> value's data over
 * to the client before a <code>SerialBlob</code> object
 * is constructed from it.  The data of an SQL <code>BLOB</code> value can
 * be materialized on the client as an array of bytes (using the method
 * <code>Blob.getBytes</code>) or as a stream of uninterpreted bytes
 * (using the method <code>Blob.getBinaryStream</code>).
 * <P>
 * <code>SerialBlob</code> methods make it possible to make a copy of a
 * <code>SerialBlob</code> object as an array of bytes or as a stream.
 * They also make it possible to locate a given pattern of bytes or a
 * <code>Blob</code> object within a <code>SerialBlob</code> object
 * and to update or truncate a <code>Blob</code> object.
 *
 * @author Jonathan Bruce
 */
public class SerialBlob implements Blob, Serializable, Cloneable {

    /**
     * A serialized array of uninterpreted bytes representing the
     * value of this <code>SerialBlob</code> object.
     * @serial
     */
    private byte buf[];

    /**
     * The internal representation of the <code>Blob</code> object on which this
     * <code>SerialBlob</code> object is based.
     */
    private Blob blob;

    /**
     * The number of bytes in this <code>SerialBlob</code> object's
     * array of bytes.
     * @serial
     */
    private long len;

    /**
     * The orginal number of bytes in this <code>SerialBlob</code> object's
     * array of bytes when it was first established.
     * @serial
     */
    private long origLen;


    public SerialBlob(byte[] b) throws SQLException {

        len = b.length;
        buf = new byte[(int)len];
        for(int i = 0; i < len; i++) {
            buf[i] = b[i];
        }
        origLen = len;
    }

    public SerialBlob (Blob blob) throws SQLException {

        if (blob == null) {
            throw new SQLException("Cannot instantiate a SerialBlob " +
                    "object with a null Blob object");
        }

        len = blob.length();
        buf = blob.getBytes(1, (int)len );
        this.blob = blob;

        //if ( len < 10240000)
        // len = 10240000;
        origLen = len;
    }

    public byte[] getBytes(long pos, int length) {
        if (length > len) {
            length = (int)len;
        }

        if (pos < 1 || len - pos < 0 ) {

        }

        pos--; // correct pos to array index

        byte[] b = new byte[length];

        for (int i = 0; i < length; i++) {
            b[i] = this.buf[(int)pos];
            pos++;
        }
        return b;
    }


    public long length() {
        return len;
    }

    public java.io.InputStream getBinaryStream() {
        InputStream stream = new ByteArrayInputStream(buf);
        return (java.io.InputStream)stream;
    }


    public long position(byte[] pattern, long start)
            throws SQLException {
        if (start < 1 || start > len) {
            return -1;
        }

        int pos = (int)start-1; // internally Blobs are stored as arrays.
        int i = 0;
        long patlen = pattern.length;

        while (pos < len) {
            if (pattern[i] == buf[pos]) {
                if (i + 1 == patlen) {
                    return (pos + 1) - (patlen - 1);
                }
                i++; pos++; // increment pos, and i
            } else if (pattern[i] != buf[pos]) {
                pos++; // increment pos only
            }
        }
        return -1; // not found
    }


    public long position(Blob pattern, long start)
            throws SQLException {
        return position(pattern.getBytes(1, (int)(pattern.length())), start);
    }

    public int setBytes(long pos, byte[] bytes)
            throws SQLException {
        return (setBytes(pos, bytes, 0, bytes.length));
    }

    public int setBytes(long pos, byte[] bytes, int offset, int length)
            throws  SQLException {

        if (offset < 0 || offset > bytes.length) {
        }

        if (pos < 1 || pos > this.length()) {
        }

        if ((long)(length) > origLen) {
        }

        if ((length + offset) > bytes.length) {

        }

        int i = 0;
        pos--; // correct to array indexing
        while ( i < length || (offset + i +1) < (bytes.length-offset) ) {
            this.buf[(int)pos + i] = bytes[offset + i ];
            i++;
        }
        return i;
    }


    public java.io.OutputStream setBinaryStream(long pos)
            throws SQLException {
        if (this.blob.setBinaryStream(pos) != null) {
            return this.blob.setBinaryStream(pos);
        } else {
            return null;
        }
    }

    public void truncate(long length)  {

        if (length > len) {
        } else if((int)length == 0) {
            buf = new byte[0];
            len = length;
        } else {
            len = length;
            buf = this.getBytes(1, (int)len);
        }
    }


    public InputStream getBinaryStream(long pos,long length) throws SQLException {
        throw new java.lang.UnsupportedOperationException("Not supported");
    }

    public void free() throws SQLException {
        throw new java.lang.UnsupportedOperationException("Not supported");
    }


    static final long serialVersionUID = -8144641928112860441L;
}