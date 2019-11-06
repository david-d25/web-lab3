"use strict";

window.Clock = function (target, options = {}) {
  let defaultOptions = {
    interval: 5000,
    axisX: '50%',
    axisY: '43.1%',
    baseImage: 'resources/default/img/clock/base.png',
    secondArrow: {
      image: 'resources/default/img/clock/second-arrow.png',
      width: '25%'
    },
    minuteArrow: {
      image: 'resources/default/img/clock/minute-arrow.png',
      width: '25%'
    },
    hourArrow: {
      image: 'resources/default/img/clock/hour-arrow.png',
      width: '25%'
    }
  };
  options = { ...defaultOptions, ...options };

  let state = {
    secondArrow: {
      targetRotation: 0,
      actualRotation: 0
    },
    minuteArrow: {
      targetRotation: 0,
      actualRotation: 0
    },
    hourArrow: {
      targetRotation: 0,
      actualRotation: 0
    }
  };

  let baseImg = document.createElement("img");
  baseImg.setAttribute("src", options.baseImage);
  baseImg.style.width = "100%";
  target.appendChild(baseImg);

  let hourArrowImg = document.createElement("img");
  hourArrowImg.setAttribute("src", options.hourArrow.image);
  hourArrowImg.style.width = options.hourArrow.width;
  hourArrowImg.style.position = "absolute";
  hourArrowImg.style.left = "0";
  hourArrowImg.style.right = "0";
  hourArrowImg.style.margin = "auto";
  target.appendChild(hourArrowImg);

  let minuteArrowImg = document.createElement("img");
  minuteArrowImg.setAttribute("src", options.minuteArrow.image);
  minuteArrowImg.style.width = options.minuteArrow.width;
  minuteArrowImg.style.position = "absolute";
  minuteArrowImg.style.left = "0";
  minuteArrowImg.style.right = "0";
  minuteArrowImg.style.margin = "auto";
  target.appendChild(minuteArrowImg);

  let secondArrowImg = document.createElement("img");
  secondArrowImg.setAttribute("src", options.secondArrow.image);
  secondArrowImg.style.width = options.secondArrow.width;
  secondArrowImg.style.position = "absolute";
  secondArrowImg.style.left = "0";
  secondArrowImg.style.right = "0";
  secondArrowImg.style.margin = "auto";
  target.appendChild(secondArrowImg);

  setTimeout(() => {
    updateArrows();
  }, 10);
  window.addEventListener("resize", updateArrows);

  setInterval(update, options.interval);
  setInterval(tick, options.interval/100);
  update();

  function update() {
    state.secondArrow.targetRotation = Date.now()/1000%60/60*360 - 90;
    state.minuteArrow.targetRotation = Date.now()/1000/60%60/60*360 - 90;
    state.hourArrow.targetRotation = new Date().getHours()%12/12*360 - 90;
    updateArrows();
  }

  function tick() {
    state.secondArrow.actualRotation += (state.secondArrow.targetRotation - state.secondArrow.actualRotation)/10;
    state.minuteArrow.actualRotation += (state.minuteArrow.targetRotation - state.minuteArrow.actualRotation)/10;
    state.hourArrow.actualRotation += (state.hourArrow.targetRotation - state.hourArrow.actualRotation)/10;
    secondArrowImg.style.transform = `rotate(${state.secondArrow.actualRotation}deg)`;
    minuteArrowImg.style.transform = `rotate(${state.minuteArrow.actualRotation}deg)`;
    hourArrowImg.style.transform = `rotate(${state.hourArrow.actualRotation}deg)`;
  }

  function updateArrows() {
    secondArrowImg.style.top = `calc(${options.axisY} - ${secondArrowImg.height/2}px)`;
    minuteArrowImg.style.top = `calc(${options.axisY} - ${minuteArrowImg.height/2}px)`;
    hourArrowImg.style.top = `calc(${options.axisY} - ${hourArrowImg.height/2}px)`;
  }
};