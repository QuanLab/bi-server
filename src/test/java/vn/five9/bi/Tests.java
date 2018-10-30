package vn.five9.bi;

import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;


public class Tests {

    @Test
    public void test() throws KettleException {

        String type = "MySql";
        String host = "localhost";
        String databaseName = "pdi_repo";
        String port = "3306";
        String userName = "root";
        String password = "mothaiba";

        String repositoryId = "pdi_repo";
        String repositoryName = "pdi_repo";

        String repUserName = "admin";
        String repPassword = "admin";

        KettleEnvironment.init();
        KettleDatabaseRepository repository = new KettleDatabaseRepository();

        DatabaseMeta databaseMeta = new DatabaseMeta(
                type, type, port, host, databaseName, port, userName, password);
        KettleDatabaseRepositoryMeta kettleDatabaseMeta = new KettleDatabaseRepositoryMeta(
                repositoryId, repositoryName, "Transformation description", databaseMeta);

        repository.init(kettleDatabaseMeta);
        repository.connect(repUserName, repPassword);

        RepositoryDirectoryInterface directory = repository.loadRepositoryDirectoryTree();

        String[] jobNames = repository.getJobNames();
        for (String jobName : jobNames) {
            JobMeta jobMeta = repository.loadJob(jobName, directory, null, null);

            System.out.println("jobMeta Description: " + jobMeta.getDescription());
            System.out.println("JobMeta Version: " + jobMeta.getJobversion());
            System.out.println("JobMeta Modify Date: " + jobMeta.getModifiedDate());
            System.out.println("JobMeta Id: " + jobMeta.getObjectId().getId());

            Job job = new Job(repository, jobMeta);
            System.out.println("Job Name: " + job.getJobname());

            job.start();
            job.waitUntilFinished();

            if (job.getErrors() != 0) {
                System.out.println("Job Error: " + job.getErrors());
                System.out.println("Error encountered!");
            }

        }
    }
}
