/*
 * @ (#) $NAME.java         5/25/2024
 *
 * Copyright (c) 2024 IUH.
 *
 */

package iuh.fit.edu.vn.analysisservice.repository;

import iuh.fit.edu.vn.analysisservice.models.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @description: ...
 * @author: Vinh Trung Pham
 * @studentID: 20119821
 * @date: 5/25/2024
 */

public interface AnalysisRepository extends JpaRepository<Analysis, Long>{
}
