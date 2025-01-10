package hospital.management.system;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.Vector;

public class DoUtils {
    public static AbstractTableModel resultSetToTableModel(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Extract column names
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnLabel(i));
            }

            // Extract row data
            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }

            return new AbstractTableModel() {
                @Override
                public int getRowCount() {
                    return data.size();
                }

                @Override
                public int getColumnCount() {
                    return columnNames.size();
                }

                @Override
                public String getColumnName(int column) {
                    return columnNames.get(column);
                }

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    return data.get(rowIndex).get(columnIndex);
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
