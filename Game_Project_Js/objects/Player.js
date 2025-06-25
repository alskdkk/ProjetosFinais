import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';

export default class Player extends Phaser.Physics.Arcade.Sprite {
  constructor(scene, x, y) {
    super(scene, x, y, 'player');
    scene.add.existing(this);
    scene.physics.add.existing(this);
    this.setCollideWorldBounds(true);
    this.cursors = scene.input.keyboard.createCursorKeys();
  }

  update() {
    this.setVelocity(0);
    if (this.cursors.left.isDown) this.setVelocityX(-160);
    else if (this.cursors.right.isDown) this.setVelocityX(160);
    if (this.cursors.up.isDown) this.setVelocityY(-160);
    else if (this.cursors.down.isDown) this.setVelocityY(160);
  }
}