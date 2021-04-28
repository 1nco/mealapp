/*
 * Mealapp api descriptor
 * This is a meal app android client.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: palko9711@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.mobilsoftlab.mealapp.network.io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Category
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-04-28T10:10:39.282Z")
public class Category {
  @SerializedName("idCategory")
  private String idCategory = null;

  @SerializedName("strCategory")
  private String strCategory = null;

  @SerializedName("strCategoryThumb")
  private String strCategoryThumb = null;

  @SerializedName("strCategoryDescription")
  private String strCategoryDescription = null;

  public Category idCategory(String idCategory) {
    this.idCategory = idCategory;
    return this;
  }

   /**
   * Get idCategory
   * @return idCategory
  **/
  @ApiModelProperty(value = "")
  public String getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(String idCategory) {
    this.idCategory = idCategory;
  }

  public Category strCategory(String strCategory) {
    this.strCategory = strCategory;
    return this;
  }

   /**
   * Get strCategory
   * @return strCategory
  **/
  @ApiModelProperty(value = "")
  public String getStrCategory() {
    return strCategory;
  }

  public void setStrCategory(String strCategory) {
    this.strCategory = strCategory;
  }

  public Category strCategoryThumb(String strCategoryThumb) {
    this.strCategoryThumb = strCategoryThumb;
    return this;
  }

   /**
   * Get strCategoryThumb
   * @return strCategoryThumb
  **/
  @ApiModelProperty(value = "")
  public String getStrCategoryThumb() {
    return strCategoryThumb;
  }

  public void setStrCategoryThumb(String strCategoryThumb) {
    this.strCategoryThumb = strCategoryThumb;
  }

  public Category strCategoryDescription(String strCategoryDescription) {
    this.strCategoryDescription = strCategoryDescription;
    return this;
  }

   /**
   * Get strCategoryDescription
   * @return strCategoryDescription
  **/
  @ApiModelProperty(value = "")
  public String getStrCategoryDescription() {
    return strCategoryDescription;
  }

  public void setStrCategoryDescription(String strCategoryDescription) {
    this.strCategoryDescription = strCategoryDescription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(this.idCategory, category.idCategory) &&
        Objects.equals(this.strCategory, category.strCategory) &&
        Objects.equals(this.strCategoryThumb, category.strCategoryThumb) &&
        Objects.equals(this.strCategoryDescription, category.strCategoryDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCategory, strCategory, strCategoryThumb, strCategoryDescription);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Category {\n");
    
    sb.append("    idCategory: ").append(toIndentedString(idCategory)).append("\n");
    sb.append("    strCategory: ").append(toIndentedString(strCategory)).append("\n");
    sb.append("    strCategoryThumb: ").append(toIndentedString(strCategoryThumb)).append("\n");
    sb.append("    strCategoryDescription: ").append(toIndentedString(strCategoryDescription)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

