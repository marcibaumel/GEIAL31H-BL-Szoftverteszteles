package hu.uni.miskolc.teszteles.beadando.dao.OfficeWorker;

import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.OfficeWorkerModel;
import hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.enums.WorkStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeWorkerRepository extends PagingAndSortingRepository<OfficeWorkerModel, Long> {
    List<OfficeWorkerModel> findOfficeWorkerModelByName(String name);
    Optional<OfficeWorkerModel> findOfficeWorkerModelByEmail(String email);
    List<OfficeWorkerModel> findOfficeWorkerModelByStatus(WorkStatus status);
}
