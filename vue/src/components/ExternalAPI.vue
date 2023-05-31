<template>
  <div>
    <a
      data-type="countdown"
      data-name="Countdown to Matchmaking:"
      data-font="Roboto"
      data-bg_color="#00ADEE"
      data-name_color="#71D96F"
      data-border_color="#888888"
      data-digits_color="#4D565E"
      data-last_unit_color="#FF6359"
      data-dt="2024-01-01 12:30:55"
      data-timezone="America/Detroit"
      class="tickcounter"
      id="no-any-link"
      style="
        display: block;
        width: 100%;
        position: relative;
        padding-bottom: 10%;
      "
      title=""
      href="//www.tickcounter.com/"
      >Countdown</a
    >
  </div>
</template>

<script>
(function(d, s, id) { var js, pjs = d.getElementsByTagName(s)[0]; if (d.getElementById(id)) return; js = d.createElement(s); js.id = id; js.src = "//www.tickcounter.com/static/js/loader.js"; pjs.parentNode.insertBefore(js, pjs); }(document, "script", "tickcounter-sdk"));

export default {
  name: "external-api",
  // data() {
  //   return {
  //     d: document,
  //     s: "script",
  //     id: "tickcounter-sdk"
  //   }
  // },
  // computed: {
  //   function() { 
  //   var js, pjs = this.d.getElementsByTagName(this.s)[0]; 
  //   if (this.d.getElementById(this.id)) return; 
  //   js = this.d.createElement(this.s); 
  //   js.id = this.id; 
  //   js.src = "//www.tickcounter.com/static/js/loader.js"; 
  //   pjs.parentNode.insertBefore(js, pjs); 
  //   return null;}
  
  // },

}
</script>

<style scoped>

</style>
