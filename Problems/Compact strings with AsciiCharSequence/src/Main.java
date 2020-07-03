import java.util.Arrays;

class AsciiCharSequence implements java.lang.CharSequence {
    byte[] bytes;

    public AsciiCharSequence(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public char charAt(int i) {
        return (char) bytes[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return new AsciiCharSequence(Arrays.copyOfRange(bytes, i, i1));
    }

    @Override
    public String toString() {
        return new String(this.bytes);
    }
}