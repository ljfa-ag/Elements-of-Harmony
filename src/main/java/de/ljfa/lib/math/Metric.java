package de.ljfa.lib.math;

/** Represents a metric in three-dimensional space, i.e. a function which computes distances. */
public interface Metric {

    double dist(double x1, double y1, double z1, double x2, double y2, double z2);
    
    /** The Manhattan metric. */
    public static final Metric l1 = new Metric() {
        @Override
        public double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
            return Math.abs(x1-x2) + Math.abs(y1-y2) + Math.abs(z1-z2);
        } 
    };
    
    /** The square of the euclidean metric. This is not a metric itself. */
    public static final Metric l2sq = new Metric() {
        @Override
        public double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
            return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) + (z1-z2)*(z1-z2);
        } 
    };
    
    /** The euclidean metric. */
    public static final Metric l2 = new Metric() {
        @Override
        public double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
            return Math.sqrt(l2sq.dist(x1, y1, z1, x2, y2, z2));
        } 
    };
    
    /** The maximum norm metric. */
    public static final Metric lInf = new Metric() {
        @Override
        public double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
            return Math.max(Math.abs(x1-x2), Math.max(Math.abs(y1-y2), Math.abs(z1-z2)));
        } 
    };
}
