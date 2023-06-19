package blackjack.domain.game.result

import blackjack.domain.participant.Participant

class WinResult(participant: Participant) : MatchResult(participant = participant, winScore = FLAG_SCORE)
