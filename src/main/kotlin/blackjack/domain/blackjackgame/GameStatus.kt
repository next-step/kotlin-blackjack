package blackjack.domain.blackjackgame

import blackjack.domain.card.PlayerDeck
import blackjack.domain.judge.Judgement
import blackjack.domain.state.Ready
import blackjack.domain.state.State

class GameStatus(val state: State = Ready(PlayerDeck()), val judgements: MutableList<Judgement> = mutableListOf())
