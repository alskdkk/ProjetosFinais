import * as Phaser from 'https://cdn.jsdelivr.net/npm/phaser@3.70.0/dist/phaser.esm.js';

export default class NPC extends Phaser.Physics.Arcade.Sprite {
  constructor(scene, x, y) {
    super(scene, x, y, 'player'); // reuse player image for NPC
    scene.add.existing(this);
    scene.physics.add.existing(this);
    this.setImmovable(true);

    this.messageShown = false;
    this.textObject = scene.add.text(x - 20, y - 40, '', {
      fontSize: '14px',
      fill: '#ffffff',
      backgroundColor: '#000000',
      padding: { x: 5, y: 2 }
    }).setScrollFactor(0).setDepth(100).setVisible(false);
  }

  interact() {
    if (!this.messageShown) {
      this.textObject.setText('Olá, aventureiro!');
      this.textObject.setVisible(true);
      this.scene.time.delayedCall(3000, () => {
        this.textObject.setVisible(false);
        this.messageShown = false;
      });
      this.messageShown = true;
    }
  }
}