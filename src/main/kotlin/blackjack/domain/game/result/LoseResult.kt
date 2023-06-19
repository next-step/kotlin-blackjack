package blackjack.domain.game.result

import blackjack.participant.Participant

class LoseResult(participant: Participant) : MatchResult(participant = participant, loseScore = FLAG_SCORE)
