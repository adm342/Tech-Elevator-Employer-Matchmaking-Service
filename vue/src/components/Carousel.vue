<template>
  <div class="container">
      <div class="images">
        <img src="../images\Bill Gates.png" alt="">
        <img src="../images\Elon Musk.png" alt="">
        <img src="../images\LornaDane.png" alt="">
        <img src="../images\Marsha Brady.png" alt="">
        <img src="../images\steve jobs.png" alt="">
        <img src="../images\steve jobs.png" alt="">
      </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>

img{
  width: 320px;
  height: 200px;
  position: absolute;
}
.container{
  width: 350px;
  height: 200px;
  position: relative;
  margin-top: 15%;
  margin-left: 45%;
  margin-bottom: 10%;
  perspective: 1000px;
  transform-style: preserve-3d;
}
.images{
  width: 100%;
  position: absolute;
  transform-style: preserve-3d;
  animation: rotate 100s linear infinite;

}

@keyframes rotate{
  from{
    

  }
  to{
    transform: rotateY(360deg);
    
  }
}
img:nth-child(1){
  transform: rotateY(60deg) translateZ(300px);
}
img:nth-child(2){
  transform: rotateY(120deg) translateZ(300px);
}
img:nth-child(3){
  transform: rotateY(180deg) translateZ(300px);
}
img:nth-child(4){
  transform: rotateY(240deg) translateZ(300px);
}
img:nth-child(5){
  transform: rotateY(300deg) translateZ(300px);
}
img:nth-child(6){
  transform: rotateY(360deg) translateZ(300px);
}
img:hover{
  width: 340px;
  height: 220px;
  cursor: pointer;
}

</style>