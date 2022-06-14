package blackjack.fixture

import blackjack.model.player.HitDecisionMaker

val AlwaysHitDecisionMaker = HitDecisionMaker { _, _ -> true }
