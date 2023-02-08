package subway.controller.response;

import lombok.Builder;
import lombok.Getter;
import subway.repository.entity.Line;
import subway.repository.entity.Station;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class LineResponse {

    private Long id;

    private String name;

    private String color;

    private List<StationResponse> stations;

    private int distance;

    public static LineResponse from(final Line line) {
        List<StationResponse> stations = line.getSections()
                .getStations()
                .stream()
                .map(StationResponse::from)
                .collect(Collectors.toList());

        return LineResponse.builder()
                .id(line.getId())
                .name(line.getName())
                .color(line.getColor())
                .stations(stations)
                .distance(line.getDistance())
                .build();
    }
}