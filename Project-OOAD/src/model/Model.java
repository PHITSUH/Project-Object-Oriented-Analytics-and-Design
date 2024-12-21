package model;

import util.Connect;

// base class untuk semua model di db
public class Model {
	// kita bikin semua model langsung bisa access connect tanpa perlu
	// ambil instance masing"
	protected static Connect connect = Connect.getInstance();
}
