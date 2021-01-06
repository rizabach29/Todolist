package pens.lab.app.belajaractivity.data.model;

public class MyUser {
        private String email;
        private String token;

        public MyUser(String email, String token) {
            this.email = email;
            this.token = token;
        }

        public String getEmail() {
            return email;
        }

        public String getToken() {
            return token;
        }
}
