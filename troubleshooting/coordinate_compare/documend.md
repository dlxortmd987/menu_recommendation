# Double 형인 좌표가 같은지 확인

소수점 6자리까지 확인

왜 6자리인가?

[출처](https://blog.naver.com/PostView.naver?blogId=jinohpark79&logNo=221170630625)
위의 블로그에서 참고했음

경도 1도는 약 88.804km, 경도 1초는 약 24.668m

따라서 10cm의 오차를 가지려면 (1 / 3600 * 246.68)도로 좌표를 표기하면 됨

이는 약 0.0000011, 즉 소수점 6자리 단위에서 10cm 씩 달라진다고 보면 됨

위도도 이와 비슷하게 소수점 6자리에서 10cm 단위로 달라짐

그럼 왜 10cm인가? 이 숫자 이하는 노이즈 수준이라 구분하는게 의미가 없다고 한다.([출처](https://als2019.tistory.com/15))

## Coordinate에서 좌표를 비교할 때 6자리까지만 비교하

```java
public record Coordinate(
	Double latitude,
	Double longitude
) {

	private static final double EPSILON = 0.000001d;

	private boolean isSameLatitude(Double latitude) {
		return Math.abs(this.latitude - latitude) < EPSILON;
	}
}


```