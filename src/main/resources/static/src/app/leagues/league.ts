export class League {
  id: string;
  leagueId: number;
  name: string;
  adminUserId: number;
  capacity: number;
  users: Array<number>;
  isLocked: boolean;
  transferTurnUserId: number;
  firstTransferUserId: number;
  transferOrder: Map<number, number>;
  inviteCode: string;
}
