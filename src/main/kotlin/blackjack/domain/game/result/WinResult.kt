package blackjack.domain.game.result

import blackjack.participant.Participant

class WinResult(participant: Participant) : MatchResult(participant = participant, winScore = FLAG_SCORE)
