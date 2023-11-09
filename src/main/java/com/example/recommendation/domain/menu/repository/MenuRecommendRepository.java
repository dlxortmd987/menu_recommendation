package com.example.recommendation.domain.menu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.menu.model.dto.FindAllMenusByConditionProjection;
import com.example.recommendation.domain.menu.model.entity.Menu;
import com.example.recommendation.domain.menu.model.entity.MenuRecommend;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;

@Repository
public interface MenuRecommendRepository extends JpaRepository<MenuRecommend, Long> {

	@Query("""
		 	select
		 	mr.menu.name as name,
		 	mr.recommendReason as reason,
		 	mr.sky as sky,
		 	mr.temperature as temperature,
		 	mr.mealTime as mealTime
			from MenuRecommend mr
			where mr.sky = :sky
			and mr.temperature = :temperature
			and mr.mealTime = :mealTime
			order by mr.recommendCount desc
		""")
	List<FindAllMenusByConditionProjection> findAllByCondition(
		@Param("sky") Sky sky,
		@Param("temperature") Temperature temperature,
		@Param("mealTime") MealTime mealTime,
		Pageable pageable
	);

	@Query("""
		 	select mr
		 	from MenuRecommend mr
		 	where mr.menu = :menu
			and mr.sky = :sky
			and mr.temperature = :temperature
			and mr.mealTime = :mealTime
		""")
	Optional<MenuRecommend> findByMenuAndCondition(
		@Param("menu") Menu menu,
		@Param("sky") Sky sky,
		@Param("temperature") Temperature temperature,
		@Param("mealTime") MealTime mealTime
	);
}
