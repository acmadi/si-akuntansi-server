package dbunit;

import org.apache.commons.lang.SystemUtils;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;

/**
 * User: pancara
 * Date: 1/13/14
 * Time: 9:09 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml"})
public class ExportDatabase {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testExportData() throws Exception {
        IDataSet dataSet = new DatabaseConnection(dataSource.getConnection()).createDataSet();

        File outputFile = new File(SystemUtils.getUserDir(), "data/dbunit/output.xml");
        FlatXmlDataSet.write(dataSet, new FileOutputStream(outputFile));

    }
}
