export default class AudioTrack {
  constructor(scene, key) {
    this.track = scene.sound.add(key);
  }

  play(loop = false) {
    this.track.play({ loop });
  }

  stop() {
    this.track.stop();
  }
}