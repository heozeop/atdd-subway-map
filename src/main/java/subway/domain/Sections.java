package subway.domain;

import subway.exception.IllegalSectionException;
import subway.exception.IllegalStationDeleteException;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Sections {

    @OneToMany
    private List<Section> sections = new ArrayList<>();

    public void addSection(Section section) {
        validateDownStationInSections(section);
        sections.add(section);
    }

    public Long getTotalDistance() {
        return sections.stream()
                .mapToLong(section  -> section.getDistance())
                .sum();
    }

    public Station getDownStation() {
        return sections.get(sections.size()-1).getDownStation();
    }

    public Station getUpStation() {
        return sections.get(0).getUpStation();
    }

    public Section deleteSection(Long stationId) {
        validateSectionsSize();
        validateStationIdDownStation(stationId);
        Section section = sections.get(sections.size()-1);
        sections.remove(sections.size()-1);
        return section;
    }

    private void validateDownStationInSections(Section section) {
        if (anySectionContainDownStationOf(section)) {
            throw new IllegalSectionException();
        };
    }

    private boolean anySectionContainDownStationOf(Section section) {
        return sections.stream()
                .anyMatch(s -> s.contains(section.getDownStation()));
    }

    private void validateSectionsSize() {
        if (sections.size() == 1) {
            throw new IllegalStationDeleteException();
        }
    }
    private void validateStationIdDownStation(Long stationId) {
        if (stationId != getDownStation().getId()) {
            throw new IllegalStationDeleteException();
        }
    }
}