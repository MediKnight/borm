/*
 * (c) Copyright 2002 Baltic Online Computer GmbH
 * All Rights Reserved.
 */
package de.bo.borm.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.bo.base.util.CurrencyNumber;
import de.bo.borm.AttributeType;

/**
 * @version 	1.0
 * @author		Jan Bernhardt (jb@baltic-online.de)
 */
public class CurrencyType implements AttributeType {
	public static final CurrencyType TYPE = new CurrencyType();
	
	public void storeAttribute(int index, PreparedStatement stmt, Object value) throws SQLException {
	    stmt.setLong(index, ((CurrencyNumber) value).longValue());
	}
	
	public Object retrieveAttribute(int index, ResultSet rs) throws SQLException {
	    return new CurrencyNumber(rs.getLong(index));
	}
	
	public String getSQLType() {
	    return "INT";
	}
}
