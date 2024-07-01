<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
  <title>学生情報登録</title>
</head>
<body>
  <div class="container mt-5">
    <h2>学生情報登録</h2>

    <form action="studentCreateExecute" method="post" onsubmit="return validateForm()">
      <!-- 入学年度 -->
      <div class="form-group">
        <label for="ent_year">入学年度</label>
        <select class="form-control" id="ent_year" name="ent_year" required>
          <!-- 今年を基準に10年前から10年後までの年リストを生成する -->
          <option value="">-------</option>
          <script>
            const currentYear = new Date().getFullYear();
            for (let year = currentYear - 10; year <= currentYear + 10; year++) {
              document.write('<option value="' + year + '">' + year + '</option>');
            }
          </script>
        </select>
        <div id="ent_year_error" class="text-danger"></div> <!-- エラーメッセージ表示用の要素 -->
      </div>

      <!-- 学生番号 -->
      <div class="form-group">
        <label for="no">学生番号</label>
        <input type="text" class="form-control" id="no" name="no" maxlength="10" placeholder="学生番号を入力してください" required>
      </div>

      <!-- 氏名 -->
      <div class="form-group">
        <label for="name">氏名</label>
        <input type="text" class="form-control" id="name" name="name" maxlength="30" placeholder="氏名を入力してください" required>
      </div>

      <!-- クラス -->
      <div class="form-group">
        <label for="class_num">クラス</label>
        <select class="form-control" id="class_num" name="class_num">
          <!-- ここにクラス番号テーブルのクラス番号を表示 -->
          <option value="101">101</option>
          <option value="102">102</option>
          <option value="103">103</option>
          <option value="104">104</option>
          <!-- 追加のクラス番号はここに追加 -->
        </select>
      </div>

      <!-- 登録して終了ボタン -->
      <button type="submit" class="btn btn-primary">登録して終了</button>

      <!-- 戻るリンク -->
      <a href="#" class="btn btn-secondary ml-2">戻る</a>
    </form>
  </div>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    function validateForm() {
      var entYear = document.getElementById("ent_year").value;
      if (entYear === "" || entYear === "-------") {
        document.getElementById("ent_year_error").textContent = "入学年度を入力して下さい。";
        return false; // フォームの送信を中止
      } else {
        document.getElementById("ent_year_error").textContent = ""; // エラーメッセージをクリア
      }
      return true; // フォームを送信
    }
  </script>
</body>
</html>
