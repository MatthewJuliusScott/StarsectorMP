
package com.dasmatarix.multiplayer.model;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The Class ExampleObject.
 */
public class ExampleObject {
	
	/** The b. */
	private boolean	b;

	/** The i. */
	private int		i;

	/** The d. */
	private double	d;
	
	/** The wb. */
	private Boolean		wb;

	/** The wi. */
	private Integer		wi;

	/** The wd. */
	private Double		wd;

	/** The ia. */
	private int[]		ia;

	/** The da. */
	private Double[]	da;

	/** The sa. */
	private String[]	sa;

	/** The ba. */
	private byte[]		ba;

	/** The ws. */
	private String		ws;

	/** The buf. */
	private ByteBuffer	buf;

	/** The bi. */
	private BigInteger	bi;

	/** The dst list. */
	List<String>		dstList;

	/** The dst map. */
	Map<String, String>	dstMap;

	/**
	 * Checks if is b.
	 *
	 * @return the b
	 */
	public boolean isB() {
		return b;
	}

	/**
	 * Sets the b.
	 *
	 * @param b the b to set
	 */
	public void setB(boolean b) {
		this.b = b;
	}

	/**
	 * Gets the i.
	 *
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * Sets the i.
	 *
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * Gets the d.
	 *
	 * @return the d
	 */
	public double getD() {
		return d;
	}

	/**
	 * Sets the d.
	 *
	 * @param d the d to set
	 */
	public void setD(double d) {
		this.d = d;
	}

	/**
	 * Gets the wb.
	 *
	 * @return the wb
	 */
	public Boolean getWb() {
		return wb;
	}

	/**
	 * Sets the wb.
	 *
	 * @param wb the wb to set
	 */
	public void setWb(Boolean wb) {
		this.wb = wb;
	}

	/**
	 * Gets the wi.
	 *
	 * @return the wi
	 */
	public Integer getWi() {
		return wi;
	}

	/**
	 * Sets the wi.
	 *
	 * @param wi the wi to set
	 */
	public void setWi(Integer wi) {
		this.wi = wi;
	}

	/**
	 * Gets the wd.
	 *
	 * @return the wd
	 */
	public Double getWd() {
		return wd;
	}

	/**
	 * Sets the wd.
	 *
	 * @param wd the wd to set
	 */
	public void setWd(Double wd) {
		this.wd = wd;
	}

	/**
	 * Gets the ia.
	 *
	 * @return the ia
	 */
	public int[] getIa() {
		return ia;
	}

	/**
	 * Sets the ia.
	 *
	 * @param ia the ia to set
	 */
	public void setIa(int[] ia) {
		this.ia = ia;
	}

	/**
	 * Gets the da.
	 *
	 * @return the da
	 */
	public Double[] getDa() {
		return da;
	}

	/**
	 * Sets the da.
	 *
	 * @param da the da to set
	 */
	public void setDa(Double[] da) {
		this.da = da;
	}

	/**
	 * Gets the sa.
	 *
	 * @return the sa
	 */
	public String[] getSa() {
		return sa;
	}

	/**
	 * Sets the sa.
	 *
	 * @param sa the sa to set
	 */
	public void setSa(String[] sa) {
		this.sa = sa;
	}

	/**
	 * Gets the ba.
	 *
	 * @return the ba
	 */
	public byte[] getBa() {
		return ba;
	}

	/**
	 * Sets the ba.
	 *
	 * @param ba the ba to set
	 */
	public void setBa(byte[] ba) {
		this.ba = ba;
	}

	/**
	 * Gets the ws.
	 *
	 * @return the ws
	 */
	public String getWs() {
		return ws;
	}

	/**
	 * Sets the ws.
	 *
	 * @param ws the ws to set
	 */
	public void setWs(String ws) {
		this.ws = ws;
	}

	/**
	 * Gets the buf.
	 *
	 * @return the buf
	 */
	public ByteBuffer getBuf() {
		return buf;
	}

	/**
	 * Sets the buf.
	 *
	 * @param buf the buf to set
	 */
	public void setBuf(ByteBuffer buf) {
		this.buf = buf;
	}

	/**
	 * Gets the bi.
	 *
	 * @return the bi
	 */
	public BigInteger getBi() {
		return bi;
	}

	/**
	 * Sets the bi.
	 *
	 * @param bi the bi to set
	 */
	public void setBi(BigInteger bi) {
		this.bi = bi;
	}

	/**
	 * Gets the dst list.
	 *
	 * @return the dstList
	 */
	public List<String> getDstList() {
		return dstList;
	}

	/**
	 * Sets the dst list.
	 *
	 * @param dstList the dstList to set
	 */
	public void setDstList(List<String> dstList) {
		this.dstList = dstList;
	}

	/**
	 * Gets the dst map.
	 *
	 * @return the dstMap
	 */
	public Map<String, String> getDstMap() {
		return dstMap;
	}

	/**
	 * Sets the dst map.
	 *
	 * @param dstMap the dstMap to set
	 */
	public void setDstMap(Map<String, String> dstMap) {
		this.dstMap = dstMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (b ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(ba);
		result = prime * result + ((bi == null) ? 0 : bi.hashCode());
		result = prime * result + ((buf == null) ? 0 : buf.hashCode());
		long temp;
		temp = Double.doubleToLongBits(d);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(da);
		result = prime * result + ((dstList == null) ? 0 : dstList.hashCode());
		result = prime * result + ((dstMap == null) ? 0 : dstMap.hashCode());
		result = prime * result + i;
		result = prime * result + Arrays.hashCode(ia);
		result = prime * result + Arrays.hashCode(sa);
		result = prime * result + ((wb == null) ? 0 : wb.hashCode());
		result = prime * result + ((wd == null) ? 0 : wd.hashCode());
		result = prime * result + ((wi == null) ? 0 : wi.hashCode());
		result = prime * result + ((ws == null) ? 0 : ws.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ExampleObject)) {
			return false;
		}
		ExampleObject other = (ExampleObject) obj;
		if (b != other.b) {
			return false;
		}
		if (!Arrays.equals(ba, other.ba)) {
			return false;
		}
		if (bi == null) {
			if (other.bi != null) {
				return false;
			}
		} else if (!bi.equals(other.bi)) {
			return false;
		}
		if (buf == null) {
			if (other.buf != null) {
				return false;
			}
		} else if (!buf.equals(other.buf)) {
			return false;
		}
		if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d)) {
			return false;
		}
		if (!Arrays.equals(da, other.da)) {
			return false;
		}
		if (dstList == null) {
			if (other.dstList != null) {
				return false;
			}
		} else if (!dstList.equals(other.dstList)) {
			return false;
		}
		if (dstMap == null) {
			if (other.dstMap != null) {
				return false;
			}
		} else if (!dstMap.equals(other.dstMap)) {
			return false;
		}
		if (i != other.i) {
			return false;
		}
		if (!Arrays.equals(ia, other.ia)) {
			return false;
		}
		if (!Arrays.equals(sa, other.sa)) {
			return false;
		}
		if (wb == null) {
			if (other.wb != null) {
				return false;
			}
		} else if (!wb.equals(other.wb)) {
			return false;
		}
		if (wd == null) {
			if (other.wd != null) {
				return false;
			}
		} else if (!wd.equals(other.wd)) {
			return false;
		}
		if (wi == null) {
			if (other.wi != null) {
				return false;
			}
		} else if (!wi.equals(other.wi)) {
			return false;
		}
		if (ws == null) {
			if (other.ws != null) {
				return false;
			}
		} else if (!ws.equals(other.ws)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExampleObject [b=" + b + ", i=" + i + ", d=" + d + ", wb=" + wb
		        + ", wi=" + wi + ", wd=" + wd + ", ia=" + Arrays.toString(ia)
		        + ", da=" + Arrays.toString(da) + ", sa=" + Arrays.toString(sa)
		        + ", ba=" + Arrays.toString(ba) + ", ws=" + ws + ", buf=" + buf
		        + ", bi=" + bi + ", dstList=" + dstList + ", dstMap=" + dstMap
		        + "]";
	}
}
