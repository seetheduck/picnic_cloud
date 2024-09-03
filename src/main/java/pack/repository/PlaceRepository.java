package pack.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pack.entity.PlaceEntity;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Integer>, JpaSpecificationExecutor<PlaceEntity> {
	
	//테마별 장소목록. 검색어 있을경우 검색어 필터링 결과값으로.
	//jpa기본제공메소드 사용하기에 생략.
//select p from PlaceEntity as p where p.placeType = :placeType
//	+ "and (:keyword is null or :keyword = '' " 
//	+ "or p.name like concat('%', :keyword, '%') " 
//	+ "or p.address like concat('%', :keyword, '%')) "
//	+ "order by p.point desc, p.likeCnt desc")
	
	//장소 페이징처리
	Page<PlaceEntity> findByPlaceType(String placeType, Pageable pageable);
	
	//선택한 장소 1곳 상세정보. 
	Optional<PlaceEntity> findByNo(@Param("no") int no);
	
	//좋아요 처리를 위한 메소드- 의도는 다르지만 상단과 같은쿼리문. 중복사용.
	//Optional<PlaceEntity> findByNo(Integer no);

	//평점 평균 계산
	@Query("SELECT AVG(r.point) FROM ReviewEntity r WHERE r.placeNo = :placeNo")
	float findAveragePointByPlaceNo(@Param("placeNo") int placeNo);

	
}
