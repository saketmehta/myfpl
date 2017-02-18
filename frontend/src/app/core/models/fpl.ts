export class Element {
  id: number;
  web_name: string;
  first_name: string;
  second_name: string;
  total_points: number;
  event_points: number;
  element_type: number;
  team: number;
}

export class Team {
  id: number;
  name: string;
  short_name: string;
}

export class ElementType {
  id: number;
  singular_name: string;
  singular_name_plural: string;
  plural_name: string;
  plural_name_plural: string;
}

export class Fixture {
  id: number;
  kickoff_time_formatted: string;
  kickoff_time: string;
  event: number;
  team_a: number;
  team_h: number;
  team_a_score: number;
  team_h_score: number;
}
