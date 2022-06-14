package blackjack.fixture

import blackjack.model.player.HitDecisionMaker

val AlwaysStayDecisionMaker = HitDecisionMaker { _, _ -> false }
