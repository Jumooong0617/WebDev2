const Header = ({ onSidebarToggle }) => {
  return (
    <header>
      <button onClick={onSidebarToggle}>Menu</button>
      <h1>CompanyABC</h1>
    </header>
  );
};

export default Header;
