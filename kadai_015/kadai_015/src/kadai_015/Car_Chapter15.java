package kadai_015;

public class Car_Chapter15 {
	// フィールド（内部データ）
	private int gear = 1; // 1速から5速のギアを表す
    private int speed = 10;  // ギアチェンジ後の速度を表す

    // コンストラクタ（初期化処理）
    public Car_Chapter15() {
    	
    }

    // 【メソッド】ギアの値により速度を変える
    // afterGear：ギア数
    public void changeGear( int afterGear ) {
    	// ギア変更
    	int beforeGear = this.gear;
    	this.gear = afterGear;
    	
    	// ギアに対応する速度設定
    	switch (this.gear) {
	    	case 1 -> this.speed = 10;
	    	case 2 -> this.speed = 20;
	    	case 3 -> this.speed = 30;
	    	case 4 -> this.speed = 40;
	    	case 5 -> this.speed = 50;
	    	default -> this.speed = 10;
		}
	    	
    	System.out.println("ギア" + beforeGear + "から" + this.gear + "に切り替えました" );
    }
    
 // 【メソッド】ギアチェンジ後の速度を表示する
    public void run() {
        System.out.println("速度は時速" + speed + "kmです" );
    }
}
