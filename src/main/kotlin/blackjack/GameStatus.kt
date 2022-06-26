package blackjack

import blackjack.judge.Judgement
import blackjack.state.Ready
import blackjack.state.State

class GameStatus(val state: State = Ready(PlayerDeck()), val judgements: MutableList<Judgement> = mutableListOf())
