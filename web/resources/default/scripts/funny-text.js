window.FunnyText = function(target, text) {
  let chars = text.split('');
  let letterEls = [];

  for (let i = 0; i < chars.length; i++) {
    let letterEl = document.createElement("span");

    letterEl.innerText = chars[i];
    letterEl.style.fontFamily = "Comic Sans MS";
    letterEl.style.textShadow = "0 0 28px black";
    letterEl.style.position = "relative";
    letterEl.style.fontSize = "48px";

    target.appendChild(letterEl);
    letterEls.push(letterEl);
  }

  setInterval(update, 1000/30);

  let iteration = 0;
  function update() {
    for (let i = 0; i < letterEls.length; i++) {
      let r = (Math.sin(i*20 + iteration/12 + 2/3*Math.PI) + 1)*127;
      let g = (Math.sin(i*20 + iteration/12 + 4/3*Math.PI) + 1)*127;
      let b = (Math.sin(i*20 + iteration/12 + 2*Math.PI) + 1)*127;
      letterEls[i].style.color = `rgb(${r}, ${g}, ${b})`;
      letterEls[i].style.fontSize = (48 + (Math.sin(i*20 + iteration/8) + 1)*24) + "px";
      letterEls[i].style.top = (-20 + (Math.sin(i*20 + iteration/5) + 1)*10) + "px";
      letterEls[i].style.left = (-20 + (Math.sin(i*20 + iteration/5) + 1)*10) + "px";
    }

    iteration++;
  }
};