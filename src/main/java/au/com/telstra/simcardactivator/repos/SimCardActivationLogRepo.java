package au.com.telstra.simcardactivator.repos;

import au.com.telstra.simcardactivator.entities.SimCardActivationLog;
import org.springframework.data.repository.CrudRepository;

public interface SimCardActivationLogRepo extends CrudRepository<SimCardActivationLog, Long> {
}
