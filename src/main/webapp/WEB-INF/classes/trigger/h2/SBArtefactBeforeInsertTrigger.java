/**
 * Copyright (C) 2017 Microbeans Software Jürgen Röder.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package trigger.h2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.api.Trigger;

/**
 * Superbowl trigger to audit changes on an entity <code>ARTEFACT</code> in
 * table <code>AUDIT</code>.
 *
 * @author mbsusr01
 */
public class SBArtefactBeforeInsertTrigger implements Trigger {

	/**
	 * Insert Constructor description here...
	 */
	public SBArtefactBeforeInsertTrigger() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.h2.api.Trigger#close()
	 */
	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.h2.api.Trigger#fire(java.sql.Connection, java.lang.Object[],
	 * java.lang.Object[])
	 * arg0 -> connection
	 * arg1 -> oldRow
	 * arg2 -> newRow
	 */
	@Override
	public void fire(Connection arg0, Object[] arg1, Object[] arg2) throws SQLException {
		BigDecimal diff = null;
		if (arg2 != null) {
			diff = (BigDecimal) arg2[1];
		}
		if (arg1 != null) {
			BigDecimal m = (BigDecimal) arg1[1];
			diff = diff == null ? m.negate() : diff.subtract(m);
		}
		PreparedStatement prep = arg0.prepareStatement("INSERT INTO AUDIT (field1, field2) " + "VALUES (?, ?)");
		prep.setBigDecimal(1, diff);
		prep.execute();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.h2.api.Trigger#init(java.sql.Connection, java.lang.String,
	 * java.lang.String, java.lang.String, boolean, int)
	 */
	@Override
	public void init(Connection arg0, String arg1, String arg2, String arg3, boolean arg4, int arg5)
			throws SQLException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.h2.api.Trigger#remove()
	 */
	@Override
	public void remove() throws SQLException {
		// TODO Auto-generated method stub
	}

}
