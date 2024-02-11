package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import simplePhysics2D.Area;
import simplePhysics2D.RigidBody;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private float mass = 1 * Game.SCALE;
	private float COR = 1 * Game.SCALE;
	private float _dcc = 0.5f * Game.SCALE;
	private float playerSpeed = 0.05f * Game.SCALE;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;

	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	private float maxSpeed = 1f * Game.SCALE;

	// Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = 0;
	private double jumpBase = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	public static float jump_buffer_time = 120;
	public static float jump_buffer_count = 0;
	public static float count_time = 0;
	float xSpeed = 0;
	float ySpeed = 0;
	private boolean inAir = false;

	public static ArrayList<Area> areas = new ArrayList<Area>();
	public static ArrayList<Player> objs = new ArrayList<Player>();

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);

	}

	public void update() {
		updatePos();
		updateAnimationTick();
		AreasCollision();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),
				width, height, null);
		// drawHitbox(g);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}

		}

	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}

		if (attacking)
			playerAction = ATTACK_1;

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;

		if (jump)
			jump();
		count_time--;
		if (count_time == 0)
			setJump(false);
		if (left && right)
			xSpeed = 0;
		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;
		if (!left && !right && !inAir) {
			if (xSpeed > 0)
				if (xSpeed - _dcc <= 0)
					xSpeed = 0;
				else
					xSpeed -= _dcc;
			else if (xSpeed < 0)
				if (xSpeed + _dcc >= 0)
					xSpeed = 0;
				else
					xSpeed += _dcc;
			xSpeed = 0;
			return;
		}
		if (!inAir)
			if (!IsEntityOnFloor(hitbox, lvlData))
				inAir = true;

		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		AreasCollision();
		ObjectCollision();
		moving = true;
		xSpeed = clamp(xSpeed, -maxSpeed, maxSpeed);
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
		System.out.println(airSpeed);
		setJump(false);
	}

	private float clamp(float val, float min, float max) {
		return Math.max(min, Math.min(max, val));
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;

	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		}

	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;

	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	protected void AreasCollision() {
		for (int i = 0; i < areas.size(); i++) {
			Area unKnowArea = areas.get(i);
			if (getBoundsX().intersects(unKnowArea) || hitbox.intersects(unKnowArea)) {

				if (xSpeed > 0) { // right
					xSpeed = 0;
					hitbox.x = unKnowArea.x - hitbox.width;
				} else if (xSpeed < 0) {// left
					xSpeed = 0;
					hitbox.x = unKnowArea.x + unKnowArea.width + 1;
				}
			}

			if (getBoundsY().intersects(unKnowArea)) {
				if (ySpeed > 0) {// down
					ySpeed = 0;
					hitbox.y = unKnowArea.y - hitbox.height;
				} else if (ySpeed < 0) { // up
					ySpeed = 0;
					hitbox.y = unKnowArea.y + unKnowArea.height + 1;
				}
			}
		}
	}

	protected void ObjectCollision() {
		for (int i = 0; i < objs.size(); i++) {
			if (objs.get(i).hashCode() == this.hashCode()) {
				continue;
			}
			Player obj = objs.get(i);
			// System.out.println(obj.hashCode());

			if (hitbox.intersects(obj.getHitbox())) {
				System.out.println("collision");
			}

			if (getBoundsX().intersects(obj.getHitbox())) {
				obj.xSpeed = 0;

				if (hitbox.intersects(obj.getHitbox())) {
					inElasticCollisionX(obj);
				}

				if (xSpeed > 0) { // right

					hitbox.x = obj.hitbox.x - hitbox.width;
				} else if (xSpeed < 0) {// left
					hitbox.x = obj.hitbox.x + obj.hitbox.width + 1;
				} else if (xSpeed == 0) {
					if (hitbox.x < obj.hitbox.x + obj.hitbox.width / 2)
						hitbox.x = obj.hitbox.x - hitbox.width;
					else if (hitbox.x > obj.hitbox.x + obj.hitbox.width / 2)
						hitbox.x = obj.hitbox.x + obj.hitbox.width;
				}
			}
			if (getBoundsY().intersects(obj.getHitbox())) {
				obj.ySpeed = 0;

				if (hitbox.intersects(obj.getHitbox())) {
					inElasticCollisionY(obj);
				}
				if (ySpeed > 0) {
					hitbox.y = obj.hitbox.y - hitbox.height;
				} else if (ySpeed < 0) {
					hitbox.y = obj.hitbox.y + obj.hitbox.height + 1;
				} else if (ySpeed == 0) {
					if (hitbox.y < obj.hitbox.y + obj.hitbox.height / 2)
						hitbox.y = obj.hitbox.y - hitbox.height;
					else if (hitbox.y > obj.hitbox.y + obj.hitbox.height / 2)
						hitbox.y = obj.hitbox.y + obj.hitbox.height;
				}
			}

		}
	}

	protected void inElasticCollisionX(Player obj) {
		float combinedMass = mass + obj.getMass();
		float newxSpeed = ((xSpeed * mass) + (obj.xSpeed * obj.mass) * COR) / combinedMass;
		obj.xSpeed = newxSpeed;

	}

	protected void inElasticCollisionY(Player obj) {
		float combinedMass = mass + obj.getMass();
		float newySpeed = ((ySpeed * mass) + (obj.ySpeed * obj.getMass()) * COR) / combinedMass;
		obj.ySpeed = newySpeed;

	}

	public Rectangle getBoundsX() {
		float bx = hitbox.x + xSpeed;
		float by = hitbox.y + 2 * Game.SCALE;
		float bw = hitbox.width;
		float bh = hitbox.height - 4 * Game.SCALE;

		return new Rectangle((int) bx, (int) by, (int) bw, (int) bh);
	}

	protected Rectangle getBoundsY() {
		float bx = hitbox.x + 2 * Game.SCALE;
		float by = hitbox.y + ySpeed;
		float bw = hitbox.width - 4 * Game.SCALE;
		float bh = hitbox.height;

		return new Rectangle((int) bx, (int) by, (int) bw, (int) bh);
	}

	public static void setInterection(Area area) {
		System.out.println("add area");
		areas.add(area);
	}

	public static void setInterection(Player obj) {
		objs.add(obj);

	}

	public static void setAreasInterection(ArrayList<Area> areas) {
		areas.addAll(areas);
	}

	public static void setObjectListInterection(ArrayList<Player> obj) {
		objs.addAll(obj);
	}

	public float getMass() {
		return mass;
	}

	public float getVelX() {
		return xSpeed;
	}

	public float getVelY() {
		return ySpeed;
	}

	public void setVelX(float velX) {
		this.xSpeed = velX;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void setMultiplier(double y) {
		jumpSpeed = (float) (jumpBase * y);
	}
}