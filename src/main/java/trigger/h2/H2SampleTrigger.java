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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.h2.api.Trigger;

/**
 * This sample application shows how to pass data to a trigger. Trigger data can
 * be persisted by storing it in the database.
 *
 * @author mbsusr01
 */
public class H2SampleTrigger implements Trigger {

	/**
	 * Insert Constructor description here...
	 */
	public H2SampleTrigger() {
		super();
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
	 */
	@Override
	public void fire(Connection arg0, Object[] arg1, Object[] arg2) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(triggerData + ": " + arg2[0]);
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
		TRIGGERS.put(getPrefix(arg0) + arg2, this);
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

	private static final Map<String, H2SampleTrigger> TRIGGERS = Collections
			.synchronizedMap(new HashMap<String, H2SampleTrigger>());

	private String triggerData;

	/**
	 * This method is called when executing this sample application from the
	 * command line.
	 *
	 * @param args
	 *            the command line parameters
	 * @throws Exception
	 *             if an error occurs
	 */
	public static void main(String... args) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
		Statement stat = conn.createStatement();
		stat.execute("CREATE TABLE TEST(ID INT)");
		stat.execute("CREATE ALIAS TRIGGER_SET FOR \"" + H2SampleTrigger.class.getName() + ".setTriggerData\"");
		stat.execute("CREATE TRIGGER T1 " + "BEFORE INSERT ON TEST " + "FOR EACH ROW CALL \""
				+ H2SampleTrigger.class.getName() + "\"");
		stat.execute("CALL TRIGGER_SET('T1', 'Hello')");
		stat.execute("INSERT INTO TEST VALUES(1)");
		stat.execute("CALL TRIGGER_SET('T1', 'World')");
		stat.execute("INSERT INTO TEST VALUES(2)");
		stat.close();
		conn.close();
	}

	/**
	 * Call this method to change a specific trigger.
	 *
	 * @param conn
	 *            the connection
	 * @param trigger
	 *            the trigger name
	 * @param data
	 *            the data
	 * @throws SQLException
	 *             if an sql error occurs
	 */
	public static void setTriggerData(Connection conn, String trigger, String data) throws SQLException {
		TRIGGERS.get(getPrefix(conn) + trigger).triggerData = data;
	}

	private static String getPrefix(Connection conn) throws SQLException {
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("call ifnull(database_path() || '_', '') || database() || '_'");
		rs.next();
		return rs.getString(1);
	}

}
