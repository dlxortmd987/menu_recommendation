package com.example.recommendation.domain.geometry.model;

import com.example.recommendation.domain.recommend.model.GeographicCoordinate;

public record GpsCoordinate(
	double x,
	double y
) {
	// 격자 간격(km)
	private static final double GRID = 5.0;

	private static final double DEGREE_TO_RADIAN = Math.PI / 180.0;

	// 지구 반경 / GRID
	private static final double EARTH_RADIUS_UNIT = 6371.00877 / GRID;

	// 투영 위도1(RADIAN)
	private static final double PROJECTION_LATITUDE1 = 30.0 * DEGREE_TO_RADIAN;
	// 투영 위도2(RADIAN)
	private static final double PROJECTION_LATITUDE2 = 60.0 * DEGREE_TO_RADIAN;
	private static final double S_N =
		Math.log(Math.cos(PROJECTION_LATITUDE1) / Math.cos(PROJECTION_LATITUDE2)) / Math.log(
			Math.tan(Math.PI * 0.25 + PROJECTION_LATITUDE2 * 0.5) / Math.tan(
				Math.PI * 0.25 + PROJECTION_LATITUDE1 * 0.5));
	private static final double S_F =
		Math.pow(Math.tan(Math.PI * 0.25 + PROJECTION_LATITUDE1 * 0.5), S_N) * Math.cos(PROJECTION_LATITUDE1) / S_N;
	// 기준점 경도(RADIAN)
	private static final double REFERENCE_POINT_LONGITUDE = 126.0 * DEGREE_TO_RADIAN;
	// 기준점 위도(RADIAN)
	private static final double REFERENCE_POINT_LATITUDE = 38.0 * DEGREE_TO_RADIAN;
	private static final double R_O =
		EARTH_RADIUS_UNIT * S_F / Math.pow((Math.tan(Math.PI * 0.25 + REFERENCE_POINT_LATITUDE * 0.5)),
			S_N);
	// 기준점 X좌표(GRID)
	private static final double REFERENCE_POINT_X = 43;
	// 기준점 Y좌표(GRID)
	private static final double REFERENCE_POINT_Y = 136;

	// 위경도 좌표 변환 공식 적용 (https://gist.github.com/fronteer-kr/14d7f779d52a21ac2f16)
	public static GpsCoordinate from(GeographicCoordinate geographicCoordinate) {
		double ra = EARTH_RADIUS_UNIT * S_F / Math.pow(
			Math.tan(Math.PI * 0.25 + (geographicCoordinate.latitude()) * DEGREE_TO_RADIAN * 0.5),
			S_N);
		double theta = geographicCoordinate.longitude() * DEGREE_TO_RADIAN - REFERENCE_POINT_LONGITUDE;

		if (theta > Math.PI)
			theta -= 2.0 * Math.PI;
		else if (theta < -Math.PI)
			theta += 2.0 * Math.PI;

		theta *= S_N;

		return new GpsCoordinate(Math.floor(ra * Math.sin(theta) + REFERENCE_POINT_X + 0.5), Math.floor(
			R_O - ra * Math.cos(theta) + REFERENCE_POINT_Y + 0.5));
	}

	public int getIntegerX() {
		return (int)x;
	}

	public int getIntegerY() {
		return (int)y;
	}
}
