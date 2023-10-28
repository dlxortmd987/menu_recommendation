package com.example.recommendation;

import org.junit.jupiter.api.Test;

import com.example.recommendation.domain.geometry.model.GpsCoordinate;
import com.example.recommendation.domain.recommend.model.GeographicCoordinate;

public class ConvertTest {

	@Test
	void test() {

		double lat = 37.6508027777777;
		double lng = 126.786566666666;
		LatXLngY tmp = convertGRID_GPS(lat, lng);
		GpsCoordinate from = GpsCoordinate.from(new GeographicCoordinate(lat, lng));

		System.out.println("tmp = " + tmp);
		System.out.println("from = " + from);
	}

	private LatXLngY convertGRID_GPS(double lat_X, double lng_Y) {
		double RE = 6371.00877; // 지구 반경(km)
		double GRID = 5.0; // 격자 간격(km)
		double SLAT1 = 30.0; // 투영 위도1(degree)
		double SLAT2 = 60.0; // 투영 위도2(degree)
		double OLON = 126.0; // 기준점 경도(degree)
		double OLAT = 38.0; // 기준점 위도(degree)
		double XO = 43; // 기준점 X좌표(GRID)
		double YO = 136; // 기1준점 Y좌표(GRID)

		//
		// LCC DFS 좌표변환 ( code : "TO_GRID"(위경도->좌표, lat_X:위도,  lng_Y:경도), "TO_GPS"(좌표->위경도,  lat_X:x, lng_Y:y) )
		//

		double DEGRAD = Math.PI / 180.0;

		double re = RE / GRID;
		double slat1 = SLAT1 * DEGRAD;
		double slat2 = SLAT2 * DEGRAD;
		double olon = OLON * DEGRAD;
		double olat = OLAT * DEGRAD;

		double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
		sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
		double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
		sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
		double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
		ro = re * sf / Math.pow(ro, sn);
		LatXLngY rs = new LatXLngY();

		rs.lat = lat_X;
		rs.lng = lng_Y;
		double ra = Math.tan(Math.PI * 0.25 + (lat_X) * DEGRAD * 0.5);
		ra = re * sf / Math.pow(ra, sn);
		double theta = lng_Y * DEGRAD - olon;
		if (theta > Math.PI)
			theta -= 2.0 * Math.PI;
		if (theta < -Math.PI)
			theta += 2.0 * Math.PI;
		theta *= sn;
		rs.x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
		rs.y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

		return rs;
	}

	class LatXLngY {
		public double lat;
		public double lng;

		public double x;
		public double y;

		@Override
		public String toString() {
			return "LatXLngY{" +
				"lat=" + lat +
				", lng=" + lng +
				", x=" + x +
				", y=" + y +
				'}';
		}
	}
}
