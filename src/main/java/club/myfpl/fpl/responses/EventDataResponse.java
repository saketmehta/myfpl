package club.myfpl.fpl.responses;

import java.util.List;
import java.util.Map;

/**
 * User: Saket
 * Date: 14/12/16
 * Time: 6:42 PM
 */
public class EventDataResponse {
    private List<FixtureResponse> fixtures;
    private Map<Long, ElementDetails> elements;

    public List<FixtureResponse> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<FixtureResponse> fixtures) {
        this.fixtures = fixtures;
    }

    public Map<Long, ElementDetails> getElements() {
        return elements;
    }

    public void setElements(Map<Long, ElementDetails> elements) {
        this.elements = elements;
    }

    public class ElementDetails {
        private ElementStats stats;

        public ElementStats getStats() {
            return stats;
        }

        public void setStats(ElementStats stats) {
            this.stats = stats;
        }

        public class ElementStats {
            private Long total_points;

            public Long getTotal_points() {
                return total_points;
            }

            public void setTotal_points(Long total_points) {
                this.total_points = total_points;
            }
        }
    }
}
