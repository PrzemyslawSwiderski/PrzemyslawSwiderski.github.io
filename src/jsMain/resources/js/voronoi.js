(async function () {
  if (CSS['paintWorklet'] === undefined) {
    await import('https://unpkg.com/css-paint-polyfill');
  }

  CSS.paintWorklet.addModule('https://unpkg.com/css-houdini-voronoi/dist/worklet.js');
})()
