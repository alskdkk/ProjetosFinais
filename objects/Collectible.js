
import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';

export default class Collectible extends Phaser.Physics.Arcade.Sprite {
  constructor(scene, x, y, texture) {
    super(scene, x, y, texture);
    scene.add.existing(this);
    scene.physics.add.existing(this);
    this.setInteractive();
    this.on('pointerdown', () => {
      this.collect();
    });
  }

  collect() {
    this.emit('collected');  // Emitindo evento corretamente
    this.setVisible(false);
    this.destroy();
  }
}
