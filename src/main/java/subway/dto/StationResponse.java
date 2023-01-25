package subway.dto;

import subway.domain.Station;

public class StationResponse {
    private Long id;
    private String name;

    public StationResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StationResponse from(Station entity) {
        return new StationResponse(entity.getId(), entity.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}