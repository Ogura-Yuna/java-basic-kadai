package com.example.springpractice.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRegisterForm {
	// 商品名
	@NotBlank(message = "商品名を入力してください。")
	private String productName;

	// 価格
	@NotNull(message = "価格を入力してください。")
	@Min(value = 100, message = "価格は100円以上で入力してください。")
	private Integer price;

	// 在庫数
	@NotNull(message = "在庫数を入力してください。")
	@Min(value = 1, message = "在庫数は1個以上で入力してください。")
	private Integer stock;
}
