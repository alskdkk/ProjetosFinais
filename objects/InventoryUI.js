export default class InventoryUI {
  constructor(scene, inventory) {
    this.scene = scene;
    this.inventory = inventory;
    this.icons = [];

    this.container = scene.add.container(10, 80);
    this.container.setDepth(1000);

    this.update();
  }

  update() {
    this.container.removeAll(true);
    this.icons = this.inventory.items.map((item, index) => {
      const icon = this.scene.add.image(index * 40, 0, item.texture).setScale(0.5);
      this.container.add(icon);
      return icon;
    });
  }

  setVisible(visible) {
    this.container.setVisible(visible);
  }

  destroy() {
    this.container.destroy();
  }
}