package ljfa.elofharmony.util;

/** Represents a position in a specific dimension */
public class DimPos {
    public int x, y, z, dim;
    
    public DimPos(int x, int y, int z, int dim) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = dim;
    }
    
    public DimPos(int x, int y, int z) {
        this(x, y, z, 0);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        DimPos other = (DimPos)obj;
        return dim == other.dim && x == other.x && z == other.z && y == other.y;
    }
    
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }
    
    @Override
    public String toString() {
        return "(DIM" + dim + ", " + x + ", " + y + ", " + z + ")";
    }
}
