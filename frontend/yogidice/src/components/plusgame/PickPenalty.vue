<template>
  <div id="penalty-box">
		<div class="penalty-list">
			<div 
			v-for="penalty in penalties"
			:key="penalty"
			id="penalties">
			{{penalty}}
			</div>
		</div>
  </div>
</template>

<script>
import { useStore } from "vuex"
export default {
  setup(){
		const store = useStore()

		let penalties = store.state.games.penalty

		return {
			penalties
		}
	}

}
</script>

<style>
#penalty-box {
	width: 90vw;
	height: 20vh;
	overflow: hidden;
}

#penalties {
	font-size: 20vw;
}
.penalty-list:hover {
	animation: pickThePenalty 2s 4 forwards;
}

.penalty-list:active {
	animation-play-state: paused;
}
@keyframes pickThePenalty {
	0% {
		transform: translateY(0);
	}
	100% {
		transform: translateY(-20vw);
	}
}



</style>