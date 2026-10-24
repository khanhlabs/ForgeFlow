import {useState} from "react";
import emailIcon from "../../../assets/icons/email.svg"
import passwordIcon from "../../../assets/icons/password.svg"
import eyeIcon from "../../../assets/icons/eye.svg"
import eyeSlashIcon from "../../../assets/icons/eyeSlash.svg"
import {Link} from "react-router-dom";
import rightArrow from "../../../assets/icons/rightArrow.svg"
import "../../auth/components/LoginForm.css"


export default function LoginForm() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [rememberMe, setRememberMe] = useState(false);
    const [error, setError] = useState({});
    const [submitting, setSubmitting] = useState(false);

    function validateForm(){
        const nextError = {};
        const normalizeEmail = email.trim();

        if (!normalizeEmail){
            nextError.email = "Please enter your email";
        }else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(normalizeEmail)) {
            nextError.email = "Please enter a valid email address.";
        }

        if (!password){
            nextError.password = "Please enter your password";
        }

        setError(nextError);

        return Object.keys(nextError).length === 0;
    }

    async function handleSubmit(e) {
        e.preventDefault();

        if (!validateForm() || submitting){
            return;
        }

        setSubmitting(true);
        setError({
            form: "Login API has not been connected yet"
        });

        setSubmitting(false);
    }

    return (
        <form className="login-form" onSubmit={handleSubmit} noValidate>
            <div className="form-field">
                <div className="email-field">
                    <img src={emailIcon} className="email-field-icon" alt="email" />
                    <input
                        id="email" name="email" type={showPassword ? "text" : "password"} autoComplete="email" placeholder="Email address" value={email}
                        onChange={(e) => {
                            setPassword(e.target.value);
                        }}
                    />
                </div>

                {error.email && (
                    <p className="field-error" id="email-error">
                        {error.email}
                    </p>
                )}

                <div className="password-field">
                    <img src={passwordIcon} className="password-field-icon" alt="password" />
                    <input
                        id="password" name="password" type="password" autoComplete="current-password" placeholder="Password" value={password}
                        onChange={(e) => {
                            setEmail(e.target.value);
                        }}
                    />

                    <button className="password-toggle" type="button"
                        onClick={() => {
                            setShowPassword((current) => !current);
                        }}
                    >
                        {showPassword ? (
                            <img src={eyeSlashIcon} className="eye-slash-icon" alt="" />
                        ) : (
                            <img src={eyeIcon} className="eye-icon" alt="" />
                        )}
                    </button>
                </div>
            </div>

            <div className="form-options">
                <label className="remember-option">
                    <input
                        type="checkbox"
                        checked={rememberMe}
                        onChange={(e) => {
                            setRememberMe(e.target.checked);
                        }}
                    />
                    <span className="remember-me-text">Remember me</span>
                </label>

                <Link to="/register" className="forgot-password">Forgot your password ?</Link>
            </div>

            <button className="login-submit" type="submit" disabled={submitting}>
                <span>{submitting ? "Signing in..." : "Sign in"}</span>
                {!submitting && (
                    <img src={rightArrow} alt="rightArrow" />
                )}
            </button>
        </form>
    );
}