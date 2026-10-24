import whiteBrandName from "../../assets/logo/full-logo-white.png"
import blackBrandName from "../../assets/logo/full-logo-black.png"
import cube from "../../assets/icons/cube.svg"
import lightning from "../../assets/icons/lightning.svg"
import checkbox from "../../assets/icons/checkbox.svg"
import group from "../../assets/icons/group.svg"
import logo from "../../assets/logo/logo.png"
import github from "../../assets/icons/github.svg"
import google from "../../assets/icons/google.svg"
import {Link} from "react-router-dom";
import LoginForm from "../../features/auth/components/LoginForm.jsx";
import "../../pages/login/LoginPage.css"

const features = [
    {
        icon: cube,
        title: "Project Management",
        description: "Keep your projects organized and on track.",
    },
    {
        icon: lightning,
        title: "Sprint Planning",
        description: "Plan, track and deliver with confidence.",
    },
    {
        icon: checkbox,
        title: "Task Collaboration",
        description: "Work together, get things done.",
    },
    {
        icon: group,
        title: "Team Productivity",
        description: "Better tools for stronger teams.",
    },
];


export default function LoginPage() {
    return (
        <main className="login-page">
            <section className="login-showcase">
                <div className="showcase-content">
                    <div className="showcase-heading">
                        <h1>
                            Build better software, <span>together.</span>
                        </h1>
                        <p>
                            Organize your development work with ForgeFlow
                            <br/>
                            Projects, sprints, tasks - all in one place.
                        </p>
                    </div>

                    <div className="feature-list">
                        {features.map((feature) => (
                            <div className="feature-item" key={feature.title}>
                                <div className={`feature-icon`}>
                                    <img src={feature.icon} alt="Feature icon" />
                                </div>

                                <div className="feature-copy">
                                    <h3>{feature.title}</h3>
                                    <p>{feature.description}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                <footer className="showcase-footer">
                    <div className="footer-decoration" aria-hidden="true">
                        <img src={logo} alt="Logo" />
                    </div>

                    <div className="footer-copy">
                        <strong>ForgeFlow</strong>
                        <p>Project Management for Developers</p>
                    </div>
                </footer>
            </section>

            <section className="login-panel">
                <div className="login-container">
                    <img className="login-logo"
                         src={blackBrandName}
                         alt="ForgeFlow"
                    />

                    <header className="login-header">
                        <h2>Welcome back</h2>
                        <p>Sign in to your account to continue.</p>
                    </header>

                    <LoginForm />

                    <div className="login-divider">
                        <span />
                        <p>or continue with</p>
                        <span />
                    </div>

                    <div className="social-login">
                        <button type="button" className="social-button" disabled>
                            <img className="github-icon" src={github} alt="GitHub" />
                        </button>

                        <button type="button" className="social-button" disabled>
                            <img className="google-icon" src={google} alt="Google" />
                        </button>
                    </div>

                    <p className="signup-prompt">
                        Don't have an account?
                        <Link to="/register">
                            Create one
                        </Link>
                    </p>

                </div>
            </section>
        </main>
    );
}